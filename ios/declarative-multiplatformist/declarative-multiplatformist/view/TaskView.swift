//
//  TaskView.swift
//  declarative-multiplatformist
//
//  Created by Pedro Santos on 15/09/2021.
//

import SwiftUI
import declarative_multiplatformist_common_ios

struct TaskView: View {
    let task: UiTask
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
    static var task =
        UiTask(content: "Content", dueTimestamp: Date().timeIntervalSince1970, isUrgent: false)
    
    static var previews: some View {
        TaskView(task: task)
            .previewLayout(.fixed(width: 400, height: 60))
    }
}
