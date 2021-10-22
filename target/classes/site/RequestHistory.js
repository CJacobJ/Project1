const hasContent = (element) => {
  return element.value;
};

const getAllRequests = (login) => {
    return fetch("http://localhost:7000/myRequests", {
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
  const requestList = document.querySelector(".request-list");
  const login = new loginAttempt (
    sessionStorage.getItem("username"),
    sessionStorage.getItem("password")
  );

  getAllRequests(login)
    .then((resp) => resp.json())
    .then((json) => {
    json.forEach((ele, index) => {
        console.log(ele);
        const requestP = document.createElement('p');       // TODO - Create table
        requestP.innerHTML = "Info: " + ele.info + "  Amount: " + ele.amount + "  Status: " + ele.approvalStatus + "  By: " + ele.approvalName + "  Reason: " + ele.reason;
        requestList.append(requestP);
    });
  });

});
