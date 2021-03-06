//
//  ContentView.swift
//  declarative-multiplatformist
//
//  Created by Pedro Santos on 02/09/2021.
//

import SwiftUI

struct TaskListView: View {
    @EnvironmentObject var taskData: TaskData
    @State private var isAddTaskPresented = false
    
    let submitAction: (String, String, Bool) -> Bool
    let invertAction: () -> Void
    let deleteAction: (IndexSet) -> Void
    
    var body: some View {
        let isEmpty = taskData.tasks.count == 0
        VStack {
            HStack {
                Button("Add task") { isAddTaskPresented = true }
                if !isEmpty {
                    Button("Invert") { invertAction() }
                }
            }
            
            if (isEmpty) {
                Text("No more tasks to do at this time. 😌")
            }
            List {
                ForEach(taskData.tasks) { task in TaskView(task: task) }
                    .onDelete { indexSet in deleteAction(indexSet) }
            }
            .sheet(
                isPresented: $isAddTaskPresented,
                onDismiss: { isAddTaskPresented = false }
            ) {
                AddTaskView() { content, dateTime, isUrgent in
                    if submitAction(content, dateTime, isUrgent) {
                        isAddTaskPresented = false
                        return true
                    } else {
                        return false
                    }
                }
            }
        }
    }
}

struct TaskListView_Previews: PreviewProvider {
    static var previews: some View {
        TaskListView(
            submitAction: {_,_,_ in true},
            invertAction: {},
            deleteAction: {_ in }
        ).environmentObject(TaskData())
    }
}
