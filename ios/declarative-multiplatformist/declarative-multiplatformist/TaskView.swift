//
//  TaskView.swift
//  declarative-multiplatformist
//
//  Created by Pedro Santos on 15/09/2021.
//

import SwiftUI

struct TaskView: View {
    let task: Task
    var body: some View {
        VStack(alignment: .leading) {
            Text(task.content)
                .font(.body)
                .foregroundColor(task.textColor)
            
            Text(task.dueString)
                .font(.caption)
        }
    }
}

struct TaskView_Previews: PreviewProvider {
    static var task = Task.data[0]
    
    static var previews: some View {
        TaskView(task: task)
            .previewLayout(.fixed(width: 400, height: 60))
    }
}
