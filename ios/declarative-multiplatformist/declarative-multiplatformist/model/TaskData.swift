//
//  TaskData.swift
//  declarative-multiplatformist
//
//  Created by Pedro Santos on 17/09/2021.
//

import Foundation
import declarative_multiplatformist_common_ios

class TaskData: ObservableObject {
    private let taskCreator = TaskCreator()
    private var sortingDirection = SortingDirection.Ascending {
        didSet {
            objectWillChange.send()
        }
    }
    
    private var _tasks: [UiTask] = [] {
        didSet {
            objectWillChange.send()
        }
    }
    
    var tasks: [UiTask] {
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
        let result = taskCreator.create(content: content, dateTime: dateTime, isUrgent: isUrgent)
        
        
        if result is TaskCreator.ResultInvalid {
            print("Invalid data")
            return false
        } else {
            _tasks.append(UiTask(task: (result as! TaskCreator.ResultSuccess).task))
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
