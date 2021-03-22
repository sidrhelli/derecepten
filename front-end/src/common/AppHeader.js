import React, { Component } from 'react';
import { Link, NavLink } from 'react-router-dom';
import './AppHeader.css';
import { localization } from '../constants/localization';

class AppHeader extends Component {
    render() {
        return (
            <header className="app-header">
                <div className="container">
                    <div className="app-branding">
                        <Link to="/" className="app-title">{localization.appname}</Link>
                    </div>
                    <div className="app-options">
                        <nav className="app-nav">
                                { this.props.authenticated ? (
                                    <ul>
                                        <li>
                                            <NavLink to="/create-recipe">{localization.create_recipe}</NavLink>
                                        </li>
                                        <li>
                                            <NavLink to="/profile">{localization.profile}</NavLink>
                                        </li>
                                        <li>
                                            <a onClick={this.props.onLogout}>{localization.logout}</a>
                                        </li>
                                    </ul>
                                ): (
                                    <ul>
                                        <li>
                                            <NavLink to="/login">{localization.login}</NavLink>        
                                        </li>
                                        <li>
                                            <NavLink to="/signup">{localization.register}</NavLink>        
                                        </li>
                                    </ul>
                                )}
                        </nav>
                    </div>
                </div>
            </header>
        )
    }
}

export default AppHeader;