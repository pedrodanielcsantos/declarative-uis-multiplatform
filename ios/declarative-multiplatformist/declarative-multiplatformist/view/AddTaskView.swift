//
//  AddTaskView.swift
//  declarative-multiplatformist
//
//  Created by Pedro Santos on 17/09/2021.
//

import SwiftUI

struct AddTaskView: View {
    @State private var content: String = ""
    @State private var dateTime: String = ""
    @State private var isUrgent: Bool = false
    
    let submitAction: (String, String, Bool) -> Bool
    
    var body: some View {
        Form {
            TextField("Content", text: $content)
            TextField("yyyy-MM-dd hh:mm", text: $dateTime)
            Toggle("Is urgent: ", isOn: $isUrgent)
            Button("Submit") { submitAction(content, dateTime, isUrgent) }
        }
        .onAppear {
            content = ""
            dateTime = ""
            isUrgent = false
        }
    }
}

struct AddTaskView_Previews: PreviewProvider {
    static var previews: some View {
        AddTaskView() {_,_,_ in true}
    }
}
