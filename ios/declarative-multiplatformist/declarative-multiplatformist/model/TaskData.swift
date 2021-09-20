//
//  TaskData.swift
//  declarative-multiplatformist
//
//  Created by Pedro Santos on 17/09/2021.
//

import Foundation

class TaskData: ObservableObject {
    private var sortingDirection = SortingDirection.Ascending {
        didSet {
            objectWillChange.send()
        }
    }
    
    private var _tasks: [Task] = [] {
        didSet {
            objectWillChange.send()
        }
    }
    
    var tasks: [Task] {
        get {
            _tasks.sorted(by: { (lhs, rhs) -> Bool in
                if sortingDirection == SortingDirection.Ascending {
                    return lhs.dueTimestamp < rhs.dueTimestamp
                } else {
                    return lhs.dueTimestamp > rhs.dueTimestamp
                }
            })
        }
    }
    
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
            _tasks.append(Task(content: content, dueTimestamp: date!.timeIntervalSince1970, isUrgent: isUrgent))
            return true
        }
    }
    
    func invert() -> Void {
        if sortingDirection == SortingDirection.Ascending {
            sortingDirection = SortingDirection.Descending
        } else {
            sortingDirection = SortingDirection.Ascending
        }
    }
    
    func delete(indexes: IndexSet) -> Void {
        _tasks.remove(atOffsets: indexes)
    }
}

private enum SortingDirection {
    case Ascending, Descending
}
