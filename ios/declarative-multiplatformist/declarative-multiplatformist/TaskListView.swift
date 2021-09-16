//
//  ContentView.swift
//  declarative-multiplatformist
//
//  Created by Pedro Santos on 02/09/2021.
//

import SwiftUI

struct TaskListView: View {
    @State var tasks: [Task]
    
    var body: some View {
        VStack {
            HStack {
                Button("Add task") { print("Add task") }
                Button("Invert") { print("Invert") }
            }
            List {
                ForEach(tasks, id: \.id) { task in
                    TaskView(task: task)
                }
                .onDelete(perform: { indexSet in
                    tasks.remove(atOffsets: indexSet)
                })
            }
        }
        
    }
}

struct TaskListView_Previews: PreviewProvider {
    static var previews: some View {
        TaskListView(tasks: Task.data)
    }
}
