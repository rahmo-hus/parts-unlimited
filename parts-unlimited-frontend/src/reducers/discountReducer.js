import {ADD_DISCOUNT} from "../actions/Types";

export default function(state= [], action){
    switch(action.type){
        case ADD_DISCOUNT :
            return action.payload;
        default :
            return state;
    }
}
