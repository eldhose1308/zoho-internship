/**
 * 7. Write a Javascript program to list the properties of a Javascript object.
 */
function listObjectProperties(myObject) {
    return Object.keys(myObject);
}

let myObject = { id: 1, name: "Eldhose", age: 24, gender: "Male" };
console.log(listObjectProperties(myObject));