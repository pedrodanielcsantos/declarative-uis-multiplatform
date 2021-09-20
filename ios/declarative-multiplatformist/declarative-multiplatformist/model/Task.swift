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
