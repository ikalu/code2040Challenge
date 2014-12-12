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

  def add_interval_to_datestamp
    collection = get_date_stamp
    print(collection)
    datestamp = collection["datestamp"].to_s
    interval = collection["interval"]
    date_in_unix = datestamp_in_unix(datestamp)
    new_date_in_unix = date_in_unix + interval
    datestamp_in_iso(new_date_in_unix)
  end

  def datestamp_in_unix(date)
    datestamp = DateTime.parse(date)
    time = Time.iso8601(datestamp.to_s)
    unix_date = time.to_i
  end

  def datestamp_in_iso(date)
    iso_date = Time.at(date).iso8601
  end

  def validate_time
    result = HTTParty.post( "http://challenge.code2040.org/api/validatetime",
                                   :body => { "token" => "8EBDqKCWgK", "datestamp" => add_interval_to_datestamp }.to_json,
                                   :headers => { 'Content-Type' => 'application/json' } )
    print(result)
  end

  def print(someThing)
    puts someThing
  end
end

game = DatingGame.new
game.validate_time
