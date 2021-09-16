//
//  Task.swift
//  declarative-multiplatformist
//
//  Created by Pedro Santos on 15/09/2021.
//

import SwiftUI

struct Task: Identifiable {
    let id: UUID = UUID()
    let content: String
    let dueTimestamp: Double
    let isUrgent: Bool
}

extension Task {
    static var data: [Task] {
        [
            Task(content: "milk", dueTimestamp: NSDate().timeIntervalSince1970, isUrgent: false),
            Task(content: "donuts", dueTimestamp: NSDate().timeIntervalSince1970, isUrgent: true),
            Task(content: "chocolate", dueTimestamp: NSDate().timeIntervalSince1970, isUrgent: false),
            Task(content: "pastry", dueTimestamp: NSDate().timeIntervalSince1970, isUrgent: false),
            Task(content: "meat", dueTimestamp: NSDate().timeIntervalSince1970, isUrgent: true),
            Task(content: "fish", dueTimestamp: NSDate().timeIntervalSince1970, isUrgent: false),
            Task(content: "milk", dueTimestamp: NSDate().timeIntervalSince1970, isUrgent: false),
            Task(content: "donuts", dueTimestamp: NSDate().timeIntervalSince1970, isUrgent: true),
            Task(content: "chocolate", dueTimestamp: NSDate().timeIntervalSince1970, isUrgent: false),
            Task(content: "pastry", dueTimestamp: NSDate().timeIntervalSince1970, isUrgent: false),
            Task(content: "meat", dueTimestamp: NSDate().timeIntervalSince1970, isUrgent: true),
            Task(content: "fish", dueTimestamp: NSDate().timeIntervalSince1970, isUrgent: false),
        ]
    }
    
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
