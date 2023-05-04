/**
 * 6. Find the word in the array by given the input and return the matches value as list
 */
function elementPresentArray(arr, element) {
    return arr.filter(arrElement => {
        if (arrElement.includes(element))
            return arrElement;
    })
}

let arr = ["Hello", "Trello", "What", "Jelly"];
let element = "ell";
console.log(elementPresentArray(arr, element));