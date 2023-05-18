/**
 * 12. Write a JavaScript program to construct the following pattern, using a nested for loop.
 *           * * * * *
 *           * * * *
 *           * * *
 *           * *
 *           *
 */
function patternStarInverted(limit) {
    for (let i = limit; i > 0; i--)
        console.log("* ".repeat(i));

}

let limit = 5;
patternStarInverted(limit);