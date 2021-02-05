package com.example.pf2inventory.Models

import java.io.Serializable

data class Item (   //  identification
                    var itemName:String,
                    var description:String,
                    var notes:String,

                    //  item access
                    var isQuickAccess:Boolean,
                    var isEquipped:Boolean,
                    var isCarried:Boolean,

                    // misc
                     var itemQuanity:Int
                ) : Serializable {

    constructor () :
            this("new item", "", "", false, false,true,1)
    constructor (itemName:String) :
            this(itemName, "", "", false, false,true,1)



}