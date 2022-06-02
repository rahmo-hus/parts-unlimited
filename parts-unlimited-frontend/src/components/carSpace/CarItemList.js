import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';
import {addToWishList, removeFromWishList} from "../../actions/Wishlist";


class CarItemList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isInWishList: false,
            car: props.car,
            isHovered: false,
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
        var {car} = this.props;
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

            <div className="product-item hover-img">
                <div className="row">
                    <div className="col-sm-12 col-md-5 col-lg-5">
                        <a className="product-img">
                            <img src={car.images[0]} alt=""/>
                        </a>
                    </div>
                    <ul className="absolute-caption">
                        <li onClick={() => this.props.fetchSingleCar(car.id)}>
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
                    <div className="col-sm-12 col-md-7 col-lg-7">
                        <div className="product-caption">
                            <h5 className="product-name" style={{backgroundColor: '#f5f5f5', textAlign:'center'}}>
                                    {car.name}
                            </h5>
                            <b className="product-price color-red">${car.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}.00</b>
                            <p className="product-txt m-t-lg-10">{car.description}</p>
                        </div>

                    </div>
                </div>
            </div>
        );
    }

};

function mapStateToProps({wishlistproducts}) {
    return {wishlistproducts};
}

export default connect(mapStateToProps, {addToWishList, removeFromWishList})(CarItemList);
