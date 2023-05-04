/**
 * 7. Write a Javascript program to list the properties of a Javascript object.
 */
function listObjectProperties(myObject) {
    let objectKeys = [];
    for (let key in myObject)
        objectKeys.push(key);

    return objectKeys;
}

let myObject = { id: 1, name: "Eldhose", age: 24, gender: "Male" };
console.log(listObjectProperties(myObject));