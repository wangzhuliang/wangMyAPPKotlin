package com.cn.wang.zl.wangmyappkotlin.bean

import android.os.Parcel
import android.os.Parcelable
import android.support.v4.app.FragmentActivity

/**
 * Created by lcling on 2018/4/3.
 *
 */
class Article(var title: String, var content: String) : Parcelable{


    constructor(parcel: Parcel) : this(
            title = parcel.readString(),
            content = parcel.readString()) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeString(title)
        dest.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }


}