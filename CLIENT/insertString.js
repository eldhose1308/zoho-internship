/**
 * 1. Write a Javascript function to insert a string within a string at a particular position (default is 1 ).
 */

function insertString(originalString, stringToInsert, position = 1) {
    position += 1;
    return originalString.slice(0, position) + stringToInsert + originalString.slice(position);
}

let originalString = "Hello World";
let stringToInsert = "my ";
let position = 5;

console.log(insertString(originalString, stringToInsert, position))