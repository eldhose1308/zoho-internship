/**
 * 5. Find if the element has present in the array.
 */
function elementPresent(arr, element) {
    return (arr.indexOf(element) > 0) ? true : false;
}

let arr = [1, 2, 3, 4, 5];
let element = 3;

console.log(elementPresent(arr, element));