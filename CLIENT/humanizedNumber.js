/**
 * 2. Write a Javascript function to humanized number (Formats a number to a human readable string) with the correct suffix such as 1st, 2nd, 3rd, 4th.
 */

function humanizedNumber(number) {
    number = Number(number);
    let unitsPlace = number % 10;
    let suffix = "";


    // 4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20
    if (number > 3 && number < 20)
        suffix = "th";
    else
        switch (unitsPlace) {
            case 1:
                suffix = "st";
                break;
            case 2:
                suffix = "nd";
                break;
            case 3:
                suffix = "rd";
                break;
            default:
                suffix = "th";

        }


    humanizedString = number + suffix;
    return humanizedString;
}

let number = 199;
console.log(humanizedNumber(number))