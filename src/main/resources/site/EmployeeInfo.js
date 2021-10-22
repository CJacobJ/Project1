const hasContent = (element) => {
  return element.value;
};

const submitLogin = (login) => {
  return fetch("http://localhost:7000/employeehomecheck", {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(login)
  });
}

class loginAttempt {
    constructor(username, password) {
        this.username = username;
        this.password = password;
    }
}

const updateInfo = (info) => {
    return fetch("http://localhost:7000/employeehomecheck", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(info)
        });
}

class employeeInfo {
    constructor(username, password, firstName, lastName, email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const inputElements = document.querySelectorAll(".input");
    const submitButton = document.querySelector("#submit-button");
    const infoForm = document.forms[0];

    const { firstname, lastname, email} =
        infoForm.elements;

    const newLogin = new loginAttempt(
        sessionStorage.getItem("username"),
        sessionStorage.getItem("password")
    );

    submitLogin(newLogin)
        .then((resp) => resp.json())
        .then((json) => {

            firstname.value = json.firstName;
            lastname.value = json.lastName;
            email.value = json.emailAddress;
        });

    submitButton.addEventListener("click", async (e) => {
          e.preventDefault();
    });
});