import org.crsh.text.Color
import org.crsh.text.Style
import java.util.regex.Pattern

class twitter {
    @Usage("show the current time")
    @Command
    void main(@Required @Argument String keyword) {
        def twitterService = context.attributes.beans['twitterService']
        def twitter = twitterService.connectionFactory().createConnection(twitterService.oAuthToken()).api;
        def pattern = Pattern.compile(Pattern.quote("#${keyword}"), Pattern.CASE_INSENSITIVE)

        twitter.searchOperations().search("#${keyword}").tweets.each { twit ->
            out << Color.blue << "@" << twit.fromUser.padRight(20) << Style.reset
            def matcher = pattern.matcher(twit.text)
            int prev = 0
            matcher.each {
                out << twit.text.substring(0, matcher.start(0))
                out << Color.red << it << Style.reset
                prev = matcher.end(0);
            }
            out << twit.text.substring(prev) << "\n";
        }
    }
}