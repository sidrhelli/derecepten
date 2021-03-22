import React, { Component } from 'react';
import './NotFound.css';
import { Link } from 'react-router-dom';
import { localization } from '../constants/localization';

class NotFound extends Component {
    render() {
        return (
            <div className="page-not-found">
                <h1 className="title">
                    {localization.four_zero_four}
                </h1>
                <div className="desc">
                    {localization.page_not_found}
                </div>
                <Link to="/"><button className="go-back-btn btn btn-primary" type="button">{localization.go_back}</button></Link>
            </div>
        );
    }
}

export default NotFound;