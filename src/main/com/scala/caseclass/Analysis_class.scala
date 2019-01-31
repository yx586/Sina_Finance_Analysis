package com.scala.caseclass

case class finance(
                  data:Seq[finance_analysis],
                  count:String
                  )

case class finance_analysis(
                              symbol: String,
                              chg: String,
                              mktcap: String,
                              cname: String,
                              diff: String,
                              volume: String,
                              market: String,
                              high: String,
                              amplitude: String,
                              category_id: String,
                              low: String,
                              pe: String,
                              price: String,
                              name: String,
                              category: String,
                              preclose: String,
                              open: String
                           )
