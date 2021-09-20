//
//  declarative_multiplatformistApp.swift
//  declarative-multiplatformist
//
//  Created by Pedro Santos on 02/09/2021.
//

import SwiftUI

@main
struct declarative_multiplatformistApp: App {
    @StateObject private var data = TaskData()
    
    var body: some Scene {
        WindowGroup {
            TaskListView(
                submitAction: data.addTask,
                invertAction: data.invert,
                deleteAction: data.delete
            ).environmentObject(data)
        }
    }
}
