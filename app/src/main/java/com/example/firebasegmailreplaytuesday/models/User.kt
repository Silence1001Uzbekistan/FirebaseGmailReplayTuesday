package com.example.firebasegmailreplaytuesday.models

class User:java.io.Serializable {
    var email: String? = null
    var display: String? = null
    var phoneNumber: String? = null
    var phoneUrl: String? = null
    var uid: String? = null

    constructor(
        email: String?,
        display: String?,
        phoneNumber: String?,
        phoneUrl: String?,
        uid: String?
    ) {
        this.email = email
        this.display = display
        this.phoneNumber = phoneNumber
        this.phoneUrl = phoneUrl
        this.uid = uid
    }

    constructor()


}
