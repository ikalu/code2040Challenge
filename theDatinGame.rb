require 'HTTParty'
require 'time'

class DatingGame

  def getDateStamp
    stack = HTTParty.post( "http://challenge.code2040.org/api/time", 
                          :body => { "token"=> "8EBDqKCWgK"}.to_json, 
                          :headers => { 'Content-Type' => 'application/json' } )

    stack["result"]
  end

  def addInterval
    collection = getDateStamp
    print(collection)
    date = collection["datestamp"]
    interval = collection["interval"].to_s
    replaceDefaultInterval(date, interval)
  #  print(time)
  #  print(interval)
  #  print(time.gsub(/00.000/, interval))
  end

  def replaceDefaultInterval(date, interval)
    date.gsub(/00.000Z/, interval)
    newDateTime = Time.utc(date)
    ((newDateTime + 0.4).round.iso8601(3))
  end

  def validateTime
    result = HTTParty.post( "http://challenge.code2040.org/api/validatetime",
                                   :body => { "token" => "8EBDqKCWgK", "datestamp" => addInterval }.to_json,
                                   :headers => { 'Content-Type' => 'application/json' } )
    print(result)
  end

  def print(someThing)
    puts someThing
  end
end

game = DatingGame.new
game.validateTime
