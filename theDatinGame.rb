## WORK IN PROGRESS...
#
#
require 'HTTParty'
require 'date'

class DatingGame

  def get_date_stamp
    stack = HTTParty.post( "http://challenge.code2040.org/api/time", 
                          :body => { "token"=> "8EBDqKCWgK"}.to_json, 
                          :headers => { 'Content-Type' => 'application/json' } )

    stack["result"]
  end

  def add_interval
    collection = getDateStamp
    print(collection)
    date = collection["datestamp"].to_s
    interval = collection["interval"].to_s
    #replaceDefaultInterval(date, interval)
    a = DateTime.parse(date)
    print(a)
    print(a.offset )
    print(a.zone)
  end

  def replace_default_interval(date, interval)
    date.gsub(/00.000Z/, interval)
    newDateTime = Time.utc(date)
    ((newDateTime + 0.4).round.iso8601(3))
  end

  def validate_time
    result = HTTParty.post( "http://challenge.code2040.org/api/validatetime",
                                   :body => { "token" => "8EBDqKCWgK", "datestamp" => add_interval }.to_json,
                                   :headers => { 'Content-Type' => 'application/json' } )
    print(result)
  end

  def print(someThing)
    puts someThing
  end
end

game = DatingGame.new
game.addInterval
