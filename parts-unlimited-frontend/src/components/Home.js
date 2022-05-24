import {Link} from "react-router-dom";
import React from "react";

export default function Home(){
    return(
        <div>
            <section className="block-sl">
                <div className="container">
                    <div className="row">
                        <div className="col-sm-12 col-md-12 col-lg-12">
                            <div className="banner-item banner-2x no-bg color-inher">
                                <h2 className="f-weight-300">More than <strong>100</strong> Vehicle available</h2>
                                <p>Select to check out offer</p>
                                <Link to={"/cars"} className="ht-btn ht-btn-default ht-btn-2x m-t-lg-35">
                                    View all cars
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    )
}
