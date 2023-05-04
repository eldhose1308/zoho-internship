/**
 * 4. Write a Javascript function to check whether an `input` is an array or not.
 */

function arrayOrNot(myVariable){
    return Array.isArray(myVariable);
}

let myVariable = [1,1];
console.log(arrayOrNot(myVariable));