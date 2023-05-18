/**
 * 6. Find the word in the array by given the input and return the matches value as list
 */
function elementPresentArray(arr, element) {
    element = element.toLowerCase();
    return arr.filter(arrElement => {
        if (arrElement.toLowerCase().includes(element))
            return arrElement;
    });
}

let arr = ["Hello", "TrEllo", "What", "Jelly"];
let element = "ell";
console.log(elementPresentArray(arr, element));