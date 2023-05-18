/**
 *  11. Write a JavaScript program to construct the following pattern, using a nested for loop.
 *           * 
 *           * *
 *           * * *
 *           * * * *
 *           * * * * *
 */

function patternStar(limit) {
    for (let i = 0; i <= limit; i++) 
        console.log("* ".repeat(i));
    
   

}

let limit = 5;
patternStar(limit);