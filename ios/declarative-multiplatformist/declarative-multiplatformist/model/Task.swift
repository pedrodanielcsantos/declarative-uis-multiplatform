//
//  Task.swift
//  declarative-multiplatformist
//
//  Created by Pedro Santos on 15/09/2021.
//

import SwiftUI
import declarative_multiplatformist_common_ios

 struct UiTask: Identifiable {
    let id: UUID = UUID()
    let content: String
    let dueTimestamp: Double
    let isUrgent: Bool
     
     init(task: Task) {
         self.content = task.content
         self.dueTimestamp = Date(milliseconds: task.dueTimestamp).timeIntervalSince1970
         self.isUrgent = task.isUrgent
     }
     
     init(content: String, dueTimestamp: Double, isUrgent: Bool) {
         self.content = content
         self.dueTimestamp = dueTimestamp
         self.isUrgent = isUrgent
     }
 }

extension UiTask {
    var dueString: String {
        get {
            let date = Date(timeIntervalSince1970: dueTimestamp)
            let dateFormatter = DateFormatter()
            dateFormatter.timeZone = TimeZone.current
            dateFormatter.locale = NSLocale.current
            dateFormatter.dateFormat = "yyyy-MM-dd HH:mm"
            return dateFormatter.string(from: date)
        }
    }
    
    var textColor: Color {
        get {
            if isUrgent{
                return Color.red
            } else {
                return Color.black
            }
        }
    }
}

extension Date {
    var millisecondsSince1970:Int64 {
        return Int64((self.timeIntervalSince1970 * 1000.0).rounded())
    }
    
    init(milliseconds:Int64) {
        self = Date(timeIntervalSince1970: TimeInterval(milliseconds) / 1000)
    }
}
