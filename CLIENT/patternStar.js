/**
 *  11. Write a JavaScript program to construct the following pattern, using a nested for loop.
 *           * 
 *           * *
 *           * * *
 *           * * * *
 *           * * * * *
 */

function patternStar(limit) {
    let pattern = '';
    for (let i = 0; i <= limit; i++) {
        pattern = '';
        for (let j = 0; j < i; j++)
            pattern += "* ";
            
        console.log(pattern);
    }

}

let limit = 5;
patternStar(limit);