/**
 * 9. Write a Javascript program counter (increment, decrement, reset option) with an object.
 */

let counterObj = {
    value: 10,
    increment_arrow: () => {
        this.value++;
    },
    increment: function () {
        this.value++;
    },
    decrement: function () {
        this.value--;
    },
    reset: function () {
        this.value = 0;
    },
}

console.log(counterObj.value);

counterObj.increment_arrow();
console.log(counterObj.value);

counterObj.increment();
console.log(counterObj.value);

counterObj.decrement();
console.log(counterObj.value);

counterObj.reset();
console.log(counterObj.value);

