/**
 * 5. Find if the element has present in the array.
 */
function elementPresent(arr, element) {
    return arr.includes(element);
}

let arr = [1, 2, 3, 4, 5];
let element = 3;

console.log(elementPresent(arr, element));