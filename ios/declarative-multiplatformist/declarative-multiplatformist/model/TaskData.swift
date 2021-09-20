//
//  TaskData.swift
//  declarative-multiplatformist
//
//  Created by Pedro Santos on 17/09/2021.
//

import Foundation

class TaskData: ObservableObject {
    @Published var tasks: [Task] = []
 
    func addTask(content: String, dateTime: String, isUrgent: Bool) -> Bool {
    let dateFormatter = DateFormatter()
        dateFormatter.timeZone = TimeZone.current
        dateFormatter.locale = NSLocale.current
        dateFormatter.dateFormat = "yyyy-MM-dd HH:mm"
        let date = dateFormatter.date(from:dateTime)
        
        if content.isEmpty || date == nil {
            print("Invalid data")
            return false
        } else {
            tasks.append(Task(content: content, dueTimestamp: date!.timeIntervalSince1970, isUrgent: isUrgent))
            return true
        }
    }
    
    func invert() -> Void {
        //TODO: Implement invert sorting order logic.
    }
}
