// demo




let objectOne = {
    'firstName': 'Eldhose',
    'lastName': 'Saji',
    sayMyName: function (greeting = 'Hi') {
        console.log(greeting, this.firstName, this.lastName);
    }
}

objectOne.sayMyName();

let objectTwo = {
    'firstName': 'Sethu',
    'lastName': 'MS',
}

objectOne.sayMyName.call(objectTwo, "Hello");
objectOne.sayMyName.apply(objectTwo, ["Gracias"]);
const ciaoSayMyName = objectOne.sayMyName.bind(objectTwo, "Ciao");
ciaoSayMyName();


return;

let users = [];

const userModel = {
    firstName: '',
    lastName: '',
}

const userController = {
    add: function (age) {
        users.push({ ...this, age });
    }
}



userModel.firstName = 'Eldhose';
userModel.lastName = 'Saji';
const addUserAge = userController.add.bind(userModel);


addUserAge(13);
addUserAge(18);

console.log(users);


return;


let fruits = ['Apple', 'Orange', 'Kiwi', 'Amla'];

let mappedFruits = fruits.map((fruit, idx, arr) => fruit.toLowerCase());
console.log(mappedFruits);

let filteredFruits = fruits.filter(fruit => fruit.startsWith('A'));
console.log(filteredFruits);

let findFruits = fruits.find(fruit => fruit.startsWith('A'));
console.log(findFruits);


let fruitsString = fruits.join("-");
console.log(fruitsString);

fruits = fruitsString.split("-");
console.log(fruits);

let arr = [1, 2, 3, 4, 5];



let reducedArr = arr.reduce(reduceFunc, 10);
console.log(reducedArr);


function reduceFunc(accumalator, value) {
    return accumalator + value;
}



let a = 'a';
b = a;

b = 'c';

let myObject = {
    'firstName': 'Eldhose',
    'lastName': 'Saji',
    sayMyName: function (passedFirstName) {
        console.log(this.firstName, this.lastName);
        console.log("passedFirstName", passedFirstName);
    }
}


function thisIsMe(myAge) {
    console.log('This is me', this.firstName, myAge);
}


let newObject = myObject;
newObject.age = 24;

console.log(myObject);

console.log(Object.keys(myObject));
console.log(Object.values(myObject));


Object.defineProperty(myObject, 'age', {
    writable: false,
});


myObject.sayMyName();

let me = {
    'firstName': 'Me',
    'lastName': 'Saji',
};


thisIsMe.call(myObject, "XYZ");
let func = thisIsMe.bind(myObject, "ABC");
func();



// myObject.sayMyName.call(me);
myObject.sayMyName.bind(me, "Sample");
// myObject.sayMyName.apply(me, ["Sample", "Name"]);

