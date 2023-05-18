/**
 * 8. Write a Javascript program to check whether the key is present or not in the object.
 */
function keyPresentInObject(myObject, keyToSearch) {
    return myObject.hasOwnProperty(keyToSearch);
}

let myObject = { id: 1, name: null, age: 24, gender: "Male" };
let keyToSearch = "name";

console.log(keyPresentInObject(myObject, keyToSearch));