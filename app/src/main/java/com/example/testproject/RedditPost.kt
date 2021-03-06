package com.example.testproject

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
class RedditPost(author:String?, dateCreate:Double?, img:String?, url:String,
                 descript:String?, count_com:Int?, str:String?, is_Video:String) {
    val author: String
    val dateCreate: String
    val img: String
    val url: String
    val descript: String
    val count_com: String
    val str: String
    val is_Video:Boolean

    init{
        if (author!= null) this.author=author
        else this.author=""

        if (dateCreate!= null) {
            var temp:Long = Instant.now().epochSecond - dateCreate.toLong()
            temp=(temp+1800-1)/3600
            if (!temp.equals(0L)) this.dateCreate="$temp hours ago"
            else this.dateCreate="<1 hours ago"
        } else this.dateCreate=""

        if(img!=null) this.img = img
        else this.img=""

        if(url!=null) this.url = url
        else this.url=""

        if(descript!=null) this.descript=descript
        else this.descript=""

        if(count_com!=null) this.count_com="$count_com comments"
        else this.count_com = "0 comments"

        if (str!= null) this.str="https://www.reddit.com$str"
        else this.str=""

        if (is_Video.equals("false")) this.is_Video=false
        else this.is_Video=true
    }
}

