/**
 * 3. Write a Javascript function to repeat a string a specified times.
 */

function repeatString(stringToRepeat,times){
    return stringToRepeat.repeat(times);
}

let stringToRepeat = "ABC";
let times = 3;
console.log(repeatString(stringToRepeat,times));