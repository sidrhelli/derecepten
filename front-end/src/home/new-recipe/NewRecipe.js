import React, { Component } from 'react';
import './NewRecipe.css';

class NewRecipe extends Component {
    constructor(props) {
        super(props);
        console.log(props);
    }
    render() {
        return (
            <div className="new-recipe-container">
                <div className="container">
                    <div className="new-recipe-info">
                        <div className="new-recipe-avatar">
                            { 
                                
                                    <div className="text-avatar">
                                        <span>Een nieuw recept</span>
                                    </div>
                                
                            }
                        </div>
                
                    </div>
                </div>    
            </div>
        );
    }
}

export default NewRecipe