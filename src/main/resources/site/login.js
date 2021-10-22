const hasContent = (element) => {
  return element.value;
};

const submitLogin = (login) => {
  return fetch("http://localhost:7000/loginattempt", {
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
  const inputElements = document.querySelectorAll(".input");
  const submitButton = document.querySelector("#submit-button");
  const loginForm = document.forms[0];

  submitButton.addEventListener("click", async (e) => {
      e.preventDefault();

    // use obj destructuring to make value access easier
    const { username, password} =
      loginForm.elements;

    const newLogin = new loginAttempt(
      username.value,
      password.value
    );

    submitLogin(newLogin)
        .then(resp => {
          if(resp.status === 200) {
               sessionStorage.setItem("username", username.value);
               sessionStorage.setItem("password", password.value);
               window.location.href = "http://localhost:7000/EmployeeHome.html";
          }
        });
  });
});

