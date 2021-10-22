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

document.addEventListener("DOMContentLoaded", () => {
    const newLogin = new loginAttempt(
        sessionStorage.getItem("username"),
        sessionStorage.getItem("password")
    );
    submitLogin(newLogin)
        .then((resp) => resp.json())
        .then((json) => {
            const nameSpan = document.querySelector(".name");
            const numSpan = document.querySelector(".reqnum");

            var nameStr = json.firstName + " " + json.lastName;

            nameSpan.textContent = nameStr;
            numSpan.textContent = (json.listLength).toString();
        })

});