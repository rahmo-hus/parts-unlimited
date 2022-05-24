import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';
import {fetchCar as fetchSingleCar} from '../../actions/Car'
import {addToWishList, removeFromWishList} from "../../actions/Wishlist";

class CarItemCard extends Component {


    constructor(props) {
        super(props);
        this.state = {
            isInWishList: false,
            car: props.car,
            isHovered: false
        };
    };

    ToggleCarWishlist(event) {
        var car = JSON.parse(event.target.id);
        if (!this.state.isInWishList) {
            this.props.addToWishList(car);
            this.setState({isInWishList: true});
        } else {
            this.props.removeFromWishList(car);
            this.setState({isInWishList: false});

        }
    }

    componentDidMount() {
        var car = this.props.car;

        if (this.props.wishlistproducts.filter(function (e) {
            return e.id === car.id
        }).length === 1) {
            this.setState({isInWishList: true})
        }
    }

    mouseHover(event) {
        this.setState({isHovered: true});
    }

    mouseUnHover(event) {
        this.setState({isHovered: false});
    }


    render() {

        const user = JSON.parse(localStorage.getItem("user"));

        var heartStyle = {};
        if (!this.state.isHovered) {
            heartStyle = {
                color: '#d11717',
                fontWeight: 'bold'
            };
        }

        const {car} = this.props;
        return (
            <div className="col-sm-12 col-md-6 col-lg-6">
                <div className="product-item hover-img">
                    <a className="product-img">
                        <img src={car.images[0]} alt=""/>
                    </a>
                    <div className="product-caption">
                        <h4 className="product-name">
                            <a>
                                {car.name} / <b>{car.condition ? car.condition.toUpperCase() : ''}</b>
                            </a>
                            <span className="f-18"> ${car.price},000</span>
                        </h4>
                    </div>
                    <ul className="absolute-caption">
                        <li>
                            <i className="fa fa-clock-o"/>{car.year}
                        </li>

                        <li>
                            <Link to={`/cars/singleproduct/${car.id}`}>
                                <i className="fa fa-search"/>View
                            </Link>
                        </li>

                        {
                            user && user.roles[0] === 'ROLE_CUSTOMER' &&
                            <li onMouseEnter={this.mouseHover.bind(this)} onMouseLeave={this.mouseUnHover.bind(this)}>
                                <i id={JSON.stringify(car)}
                                   className={this.state.isInWishList ? "fa fa-heart" : "fa fa-heart-o"}
                                   style={heartStyle}
                                   onClick={this.ToggleCarWishlist.bind(this)}
                                />WishList
                            </li>
                        }

                    </ul>
                </div>
            </div>

        );
    }

}

function mapStateToProps({wishlistproducts}) {
    return {wishlistproducts};
}


export default connect(mapStateToProps, {fetchSingleCar, addToWishList, removeFromWishList})(CarItemCard);
