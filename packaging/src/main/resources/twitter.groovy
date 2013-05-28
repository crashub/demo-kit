import groovy.json.*
import java.util.regex.Pattern
import org.crsh.text.*

class twitter {

  @Command
  public void main(@Required @Argument String keyword) {
    def data = context.attributes.beans['twitterService'].search(keyword)
    def slurper = new JsonSlurper();
    def json = slurper.parseText(data)

    def pattern = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE)
    json.results.each { twit ->
      out << Color.blue << twit.from_user.padRight(20) << Style.reset
      def matcher = pattern.matcher(twit.text)
      int prev = 0
      matcher.each {
        out << twit.text.substring(0, matcher.start(0))
        out << Color.red << it << Style.reset
        prev = matcher.end(0);
      }
      out << twit.text.substring(prev);

      out << "\n";
    }
  }
}
