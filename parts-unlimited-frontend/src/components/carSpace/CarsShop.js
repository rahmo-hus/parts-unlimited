import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchCars} from '../../actions/Car';
import CarsContainer from './CarsContainer';
import {Route, Routes} from "react-router-dom";
import CarDetails from "./CarDetails";

class CarShop extends Component {
    componentDidMount() {
        this.props.fetchCars();
    }

    render() {
        return (
            <div className="wrap-body-inner" style={{minHeight: '1200px'}}>
                <Routes>
                    <Route path="/"
                           element={<CarsContainer filters={this.props.filters} carsList={this.props.cars}/>}/>
                    <Route exact path="/singlproduct" element={<CarDetails/>}/>
                </Routes>
            </div>
        );

    }
}


function mapStateToProps(state) {
    return {cars: state.cars};
}

export default connect(mapStateToProps, {fetchCars})(CarShop);
