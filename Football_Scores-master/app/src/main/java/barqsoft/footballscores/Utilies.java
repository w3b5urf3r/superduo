package barqsoft.footballscores;

import android.content.Context;
import android.content.res.Resources;

import java.text.DecimalFormat;

/**
 * Created by yehya khaled on 3/3/2015.
 */
public class Utilies
{
    public static final int SERIE_A = 357;
    public static final int PREMIER_LEGAUE = 354;
    public static final int CHAMPIONS_LEAGUE = 362;
    public static final int PRIMERA_DIVISION = 358;
    public static final int BUNDESLIGA = 351;
    public static String getLeague(Context mContext, int league_num)
    {
        switch (league_num)
        {
            case SERIE_A : return mContext.getResources().getString(R.string.seriaa);
            case PREMIER_LEGAUE : return mContext.getResources().getString(R.string.premierleague);
            case CHAMPIONS_LEAGUE : return mContext.getResources().getString(R.string.champions_league);
            case PRIMERA_DIVISION : return mContext.getResources().getString(R.string.primeradivison);
            case BUNDESLIGA : return mContext.getResources().getString(R.string.bundesliga);
            default: return mContext.getString(R.string.not_known_league_report);
        }
    }
    public static String getMatchDay(Resources res, int match_day, int league_num)
    {
        if(league_num == CHAMPIONS_LEAGUE)
        {
            if (match_day <= 6)
            {
                return String.format(res.getConfiguration().locale,res.getString(R.string.group_stage_match_6), DecimalFormat.getInstance(res.getConfiguration().locale).format(6));
            }
            else if(match_day == 7 || match_day == 8)
            {
                return res.getString(R.string.first_knockout_round);
            }
            else if(match_day == 9 || match_day == 10)
            {
                return res.getString(R.string.quarter_final);
            }
            else if(match_day == 11 || match_day == 12)
            {
                return res.getString(R.string.semi_final);
            }
            else
            {
                return res.getString(R.string.final_text);
            }
        }
        else
        {
            return String.format(res.getConfiguration().locale,res.getString(R.string.match_day_format) ,  DecimalFormat.getInstance(res.getConfiguration().locale).format(match_day));
        }
    }

    public static String getScores(Resources res,int home_goals,int awaygoals)
    {
        if(home_goals < 0 || awaygoals < 0)
        {
            return " - ";
        }
        else
        {
            return DecimalFormat.getInstance(res.getConfiguration().locale).format(home_goals) + " - " + DecimalFormat.getInstance(res.getConfiguration().locale).format(awaygoals);
        }
    }

    public static int getTeamCrestByTeamName (String teamname)
    {
        if (teamname==null){return R.drawable.no_icon;}
        switch (teamname)
        { //This is the set of icons that are currently in the app. Feel free to find and add more
            //as you go.
            case "Arsenal London FC" : return R.drawable.arsenal;
            case "Manchester United FC" : return R.drawable.manchester_united;
            case "Swansea City" : return R.drawable.swansea_city_afc;
            case "Leicester City" : return R.drawable.leicester_city_fc_hd_logo;
            case "Everton FC" : return R.drawable.everton_fc_logo1;
            case "West Ham United FC" : return R.drawable.west_ham;
            case "Tottenham Hotspur FC" : return R.drawable.tottenham_hotspur;
            case "West Bromwich Albion" : return R.drawable.west_bromwich_albion_hd_logo;
            case "Sunderland AFC" : return R.drawable.sunderland;
            case "Stoke City FC" : return R.drawable.stoke_city;
            default: return R.drawable.no_icon;
        }
    }
}
