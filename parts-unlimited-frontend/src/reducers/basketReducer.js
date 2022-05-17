import {BASKET_ADD, BASKET_EDIT_ITEM, BASKET_REMOVE_ITEM} from "../actions/Types";

export default function (state = [], action) {
    switch (action.type) {
        case BASKET_ADD:
            var {product, quantity} = action.payload;
            var exist = false;
            var currentQuantity = 0;


            state.forEach(e => {
                if (e.product.id === product.id) {
                    currentQuantity = e.quantity;
                    exist = true;
                }
            });
            if (exist) {
                state = state.filter(function (e) {
                    return e.product.id !== product.id;
                });
                currentQuantity += quantity;
                return [{product, quantity: currentQuantity}, ...state];
            } else {
                return [{product, quantity}, ...state];
            }
        case BASKET_REMOVE_ITEM:
            state = state.filter(function (item) {
                return item.product.id !== action.payload;
            });
            return state;

        case BASKET_EDIT_ITEM:
            var newstate = [];
            var {product, quantity} = action.payload;
            console.log(state);
            state.map(function (item) {
                if (item.product.id !== action.payload.product.id)
                    newstate.push(item);
                else (newstate.push({product, quantity}));
            });

            state = newstate;
            return newstate;

        default:
            return state;
    }
}
