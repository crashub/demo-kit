import groovy.json.*

class twitter {
  @Command
  public void main(@Required @Argument String keyword) {
    def data = context.attributes.beans['twitterService'].search(keyword)
    def slurper = new JsonSlurper();
    def json = slurper.parseText(data)

    json.results.each { twit ->
      out << "----------------------\n" << twit.text << "\n";
    }
  }
}
