const hasContent = (element) => {
  return element.value;
};

const submitRequest = (request) => {
    return fetch("http://localhost:7000/addRequest", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(request)
      });
}

class NewRequest {
    constructor(username, password, amount, info) {
        this.username = username;
        this.password = password;
        this.amount = amount;
        this.info = info;
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const submitButton = document.querySelector("#submit-button");
    const requestForm = document.forms[0];
    const toast = document.querySelector(".toast");

    submitButton.addEventListener("click", async (e) => {
        e.preventDefault();

        const{ amount, info } = requestForm.elements;

        const request = new NewRequest(
            sessionStorage.getItem("username"),
            sessionStorage.getItem("password"),
            amount.value,
            info.value
        );

        toast.querySelector(".toast-content p").textContent = "2";

        submitRequest(request)
            .then(resp => {
                if(resp.status === 201) {
                    toast.querySelector(".toast-content p").textContent =
                        "Your request has been submitted";
                    toast.classList.toggle("activate");
                    setTimeout(() => toast.classList.toggle("activate"), 2000);
                } else {
                    toast.querySelector(".toast-content p").textContent =
                        "Request submission failed";
                    toast.classList.toggle("activate");
                    setTimeout(() => toast.classList.toggle("activate"), 2000);
                }
            });
    });
});
