import React, {Component} from 'react';
import Header from './Header';
import Footer from './Footer';
import { Link } from 'react-router-dom';

class SharedLayout extends Component{

    getFilters(filters){
        this.props.carsfilters(filters);
    }

    renderSearchBar(){
        const path=window.location.href.split('/')[3];
        if(path==='home' || path===''){
            return  (
                <div>
                    <section className="block-sl">
                        <div className="container">
                            <div className="row">
                                <div className="col-sm-12 col-md-12 col-lg-12">
                                    <div className="banner-item banner-2x no-bg color-inher">
                                        <h2 className="f-weight-300">More than <strong>100</strong> Vehicle available</h2>
                                        <p>Select to check out our offer</p>
                                        <Link to={"/cars"} className="ht-btn ht-btn-default ht-btn-2x m-t-lg-35">
                                            View all cars
                                        </Link>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            );
        }
    }


    render(){
        return (
            <div id="wrap"  className="color1-inher">
                <Header/>
                {/*{this.renderSearchBar()}*/}
                <div id="wrap-body" className="p-t-lg-45">
                    <div className="container">
                        {this.props.children}
                    </div>
                </div>
                <Footer/>
            </div>
        );
    }

}
export default SharedLayout;
