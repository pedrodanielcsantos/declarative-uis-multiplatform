//
//  declarative_multiplatformistApp.swift
//  declarative-multiplatformist
//
//  Created by Pedro Santos on 02/09/2021.
//

import SwiftUI

@main
struct declarative_multiplatformistApp: App {
    var body: some Scene {
        WindowGroup {
            TaskListView(tasks: Task.data)
        }
    }
}
