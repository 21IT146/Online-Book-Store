// a.portno = undefined;
// const addS= async (username, password) => {
//     console.log("add");

    // const response = await fetch(a.portno+'/add', {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json'
    //     },
    //     body: JSON.stringify({username,password})
    // });
    // const json2 = response.json();
    // console.log(json2);
    fetch("https://localhost:8080/form", {
    method: "POST",
    body: JSON.stringify({
        "username":document.getElementById(exampleInputUserName).value,
        "password":document.getElementById(exampleInputPassword2).value
    }),
    headers: {
        "Content-type": "application/json; charset=UTF-8"
    }
});


const date=new Date();
function onsub() {
        const curpass = document.getElementById('exampleInputPassword1').value+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds()
        console.log("hello "+curpass)
    }
